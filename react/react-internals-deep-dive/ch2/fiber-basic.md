## core components

(only consider the DOM use case)

### Fiber components

React uses this tree structure for representing DOM

FiberRootNode: root of fiber tree, fiberRootNode.current is referred to hostRootFiber <br/>
HostRootFiber: fiber with tag as HostRoot(3)

#### Fiber tag

just list some of tag types:

```
FunctionComponent = 0
ClassComponent = 1
HostRoot = 3
HostComponent = 5
SuspenseComponent = 13
LazyComponent = 16
DehydratedFragment = 18
```

## Flow

### createRoot

```js
ReactDOM.createRoot(document.getElementById("root")).render(<App />);

// createRoot
export function createRoot(
  container: Element | Document | DocumentFragment,
  options?: CreateRootOptions
): RootType {
  ...
  const root = createContainer(container);
  ...
  return new ReactDOMRoot(root);
}
```

### createContainer

defined in \react-18.2.0\packages\react-reconciler\src\ReactFiberReconciler.old.js

```js
export function createContainer(containerInfo) {
  ...
  return createFiberRoot(containerInfo);
}
```

### createFiberRoot

in ReactFiberRoot.old.js

```js
export function createFiberRoot(containerInfo):FiberRoot {
  ...
  const root: FiberRoot = new FiberRootNode()
  ...
  root.current = createHostRootFiber()
  return root
}
```

### createHostRootFiber -> FiberNode

a constructor function

```js
// # TODO: add descriptions for the essential props of FiberNode
function FiberNode(
  tag: WorkTag,
  pendingProps: mixed,
  key: null | string,
  mode: TypeOfMode
) {
  // Instance
  this.tag = tag; // Fiber tag type
  this.key = key; // used for reconciliation
  this.elementType = null;
  this.type = null;
  this.stateNode = null;

  // Fiber
  this.return = null;
  this.child = null;
  this.sibling = null;
  this.index = 0;

  this.ref = null;

  this.pendingProps = pendingProps;
  this.memoizedProps = null;
  this.updateQueue = null;
  this.memoizedState = null;
  this.dependencies = null;

  this.mode = mode;

  // Effects
  this.flags = NoFlags;
  this.subtreeFlags = NoFlags;
  this.deletions = null;

  this.lanes = NoLanes;
  this.childLanes = NoLanes;

  this.alternate = null; // for WIP fiber to reference current fiber
  ...
}
```
