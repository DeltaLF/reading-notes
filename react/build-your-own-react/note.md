## Component

```js
// effectTag
type EffectTag: 'PLACEMENT' | 'UPDATE' | 'DELETION'

// list fiber props here
Fiber: {
  dom: DOM_ELEMENT,
  type: HTML_TAG_TYPE | 'TEXT_ELEMENT' | renderingFunction
  // renderingFunction for functional component
  props:{
    children: [] // won't be assigned to DOM element in createDom function
  }
  parent: parentFiber,
  sibling: siblingFiber,
  alternate: fiber, // link between current fiber and wip fiber (for reconcile)
  effectTag: effectTag // after reconciliation, element transform to fiber with this tag to determine the action in the commit phase
}

// for effectTag: deletion, there is no new fiber, so there should be an array to record DOM nodes to be deleted


```

## I: Element (React.createElement)

```jsx
const element = {
  type: 'h1',
  props:{
    title: 'h1 title',
    children: anotherElement
  }
}
=> React.createElement("h1", {title:"h1 title"}, anotherElement)
```

## II: Render

```jsx
/* 
what does render do? (to be confirmed)
1. traversal elements
2. execute the code in component

a component is:
some js code + createElement (return) 
*/
ReactDOM.render(element, container);

// inital functionality
function render(element, container) {
  const dom = document.createElement(element.type);
  container.appendChild(dom);
  // assign element props to dom
  const isProperty = (key) => key !== "children";
  Object.keys(element.props)
    .filter(isProperty)
    .forEach((name) => {
      dom[name] = element.props[name];
    });
  // continuesly render child components
  element.props.children.forEach((childElement) => {
    render(childElement, dom);
  });
}
```

## III: Concurrent mode

From the code so far, everything is executed synchronously and it will be problematic if the tree go deeper and more complexity

⇒ break work into smaller pieces

### Note: requestIdleCallback is replaced with https://github.com/facebook/react/tree/main/packages/scheduler in react

```jsx
let nextUnitOfWork = null;
function workLoop(deadline) {
  let shouldYouYield = false;
  while (nextUnitOfWork && !shouldYield) {
    nextUnitOfWork = performunitOfWork(nextUnitOfWork);
    shouldYield = deadline.timeRemaining() < 1;
  }
  requestIdleCallback(workLoop);
}

function performunitOfWork() {}
```

## IV: Fibers (DFT child → sibling → parent)

to organize the units of work

```jsx
/*
1. one fiber for one element (not node)
2. one fiber for one unit of work
*/

Didact.render(
  <div>
    <h1>
      <p />
      <a />
    </h1>
    <h2 />
  </div>,
  container
)

0. create root fiber in render and set it as the nextUnitOfWork
loop:
1. add the element to the DOM
2. create the fibers for the element’s children
3. select the next unit of work
```

### refactor:

createDom

```jsx
function createDom(fiber) {
  const dom =
    fiber.type == "TEXT_ELEMENT"
      ? document.createTextNode("")
      : document.createElement(fiber.type);

  const isProperty = (key) => key !== "children";
  // map element props to node (DOM element)
  Object.keys(fiber.props)
    .filter(isProperty)
    .forEach((name) => {
      dom[name] = fiber.props[name];
    });

  return dom;
}
```

performUnitOfWork: a recursive function to depth first traversal through fiber

### problem: In the process of traversing and create dom, there might be incomplete UI (element node)

```jsx
const nextFiber = performUnitOfWork(currentFiber);

function performUnitOfWork(fiber) {
  if (!fiber.dom) {
    fiber.dom = createDom(fiber);
  }

  /*
  After the new dom is created, we can store it as wip fiber
  so we can perform reconciliation 
  */
  if (fiber.parent) {
    fiber.parent.dom.appendChild(fiber.dom);
  }

  const elements = fiber.props.children;
  /*   
    reconcileChildren(fiber, elements) 
  */

  let index = 0;
  let prevSibling = null;

  while (index < elements.length) {
    const element = elements[index];

    const newFiber = {
      type: element.type,
      props: element.props,
      parent: fiber,
      dom: null,
    };

    if (index === 0) {
      fiber.child = newFiber;
    } else {
      prevSibling.sibling = newFiber;
    }

    prevSibling = newFiber;
    index++;
  }

  if (fiber.child) {
    return fiber.child;
  }
  let nextFiber = fiber;
  while (nextFiber) {
    if (nextFiber.sibling) {
      return nextFiber.sibling;
    }
    nextFiber = nextFiber.parent;
  }
}
```

## V: Render and Commit phases

render and commit phase needed to be separated because we don’t want incomplete UI component to be appended too early.

```jsx
function workLoop(deadline) {
  let shouldYield = false;
  while (nextUnitOfWork && !shouldYield) {
    nextUnitOfWork = performUnitOfWork(nextUnitOfWork);
    shouldYield = deadline.timeRemaining() < 1;
  }

  if (!nextUnitOfWork && wipRoot) {
    commitRoot(); // only commit the change while there is no nextUnitOfWork
  }

  requestIdleCallback(workLoop);
}
```

## VI: Reconciliation

comparison between old(current) and wip (new) fiber

```jsx
function commitRoot() {
  commitWork(wipRoot.child);
  wipRoot = null;
}

function commitWork(fiber) {
  if (!fiber) {
    return;
  }
  const domParent = fiber.parent.dom;
  domParent.appendChild(fiber.dom);
  commitWork(fiber.child);
  commitWork(fiber.sibling);
}
```

reconcileChildren

element stands for React elements which comes from fiber.props.children

```jsx
function reconcileChildren(wipFiber, elements) {
  let index = 0;
  let oldFiber = wipFiber.alternate && wipFiber.alternate.child;
  let prevSibling = null;

  while (index < elements.length || oldFiber != null) {
    const element = elements[index];
    let newFiber = null;

    const sameType = oldFiber && element && element.type == oldFiber.type;

    if (sameType) {
      newFiber = {
        type: oldFiber.type,
        props: element.props,
        dom: oldFiber.dom,
        parent: wipFiber,
        alternate: oldFiber,
        effectTag: "UPDATE",
      };
    }
    if (element && !sameType) {
      newFiber = {
        type: element.type,
        props: element.props,
        dom: null,
        parent: wipFiber,
        alternate: null,
        effectTag: "PLACEMENT",
      };
    }
    if (oldFiber && !sameType) {
      oldFiber.effectTag = "DELETION";
      deletions.push(oldFiber);
    }

    if (oldFiber) {
      oldFiber = oldFiber.sibling;
    }

    if (index === 0) {
      wipFiber.child = newFiber;
    } else if (element) {
      prevSibling.sibling = newFiber;
    }

    prevSibling = newFiber;
    index++;
  }
}
```

## VII: Function Components

function is like an extra layer it is different from directly input jsx by:

1. the fiber from functional component doesn’t come with a DOM node
2. the children is from the return value instead of its props

So for the fiber that represents functional component, we need to treat differently

### problem

Function Components fiber doesn’t have corresponding dom and this raises two problems:

1. while commit create new DOM node ⇒ appendChild for the parent fiber of functional component fiber

```jsx
let domParentFiber = fiber.parent;
while (!domParentFiber.dom) {
  domParentFiber = domParentFiber.parent;
}
const domParent = domParentFiber.dom;
```

1. while commit delete ⇒ remove dom element for the “child” of functional component fiber (but not functional component fiber itself)

```jsx
function commitDeletion(fiber, domParent) {
  if (fiber.dom) {
    domParent.removeChild(fiber.dom);
  } else {
    commitDeletion(fiber.child, domParent);
  }
}
```

```jsx
function performUnitOfWork(fiber) {
  const isFunctionComponent =
    fiber.type instanceof Function
  if (isFunctionComponent) {
    updateFunctionComponent(fiber)
  } else {
    updateHostComponent(fiber)
  }
  ...
}

function updateFunctionComponent(fiber) {
  // TODO
}

function updateHostComponent(fiber) {
  if (!fiber.dom) {
    fiber.dom = createDom(fiber)
  }
  reconcileChildren(fiber, fiber.props.children)
}

function updateFunctionComponent(fiber) {
  const children = [fiber.type(fiber.props)]
  reconcileChildren(fiber, children)
}

```
