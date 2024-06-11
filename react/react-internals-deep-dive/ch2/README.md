## core components

(only consider the DOM use case)

### Fiber components

React uses this tree structure for representing DOM

FiberRootNode: root of fiber tree, fiberRootNode.current is referred to hostRootFiber <br/>
?HostRootFiber:

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

```js
export function createFiberRoot(containerInfo):FiberRoot {
  ...
  const root: FiberRoot = new FiberRootNode()
  ...
  root.current = createHostRootFiber()
  return root
}
```
