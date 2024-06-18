# Render

After the fiber, Root are created, the next step is to render

```js
ReactDOM.createRoot(document.getElementById("root")).render;
```

## related components

context:  
lane: bitmask number for scheduling priority
update: A linked list structure which contains a property payload that is the React component user defined

## render

### ReactDOMRoot.prototype.render

in ReactDOMRoot.js

```js
ReactDOMHydrationRoot.prototype.render = ReactDOMRoot.prototype.render =
  function (children: ReactNodeList): void {
    const root = this._internalRoot;
    updateContainer(children, root, null, null);
  };
```

### updateContainer

in ReactFiberReconciler.old.js

```js
export function updateContainer(
  element: ReactNodeList, // The React components users defined
  container: OpaqueRoot,
  parentComponent: ?React$Component<any, any>,
  callback: ?Function
): Lane {
  const current = container.current;
  const eventTime = requestEventTime();
  const lane = requestUpdateLane(current);
  const context = getContextForSubtree(parentComponent);

  const update = createUpdate(eventTime, lane);
  update.payload = { element };
  callback = callback === undefined ? null : callback;
  const root = enqueueUpdate(current, update, lane);
  if (root !== null) {
    scheduleUpdateOnFiber(root, current, lane, eventTime);
    entangleTransitions(root, current, lane);
  }

  return lane;
}
```

### update, enqueueUpdate

ReactFiberClassUpdateQueue.old.js

```js
export type Update<State> = {|
  eventTime: number,
  lane: Lane,
  tag: 0 | 1 | 2 | 3,
  payload: any, // react component defined by users
  callback: (() => mixed) | null,
  next: Update<State> | null,
|};

export function createUpdate(eventTime: number, lane: Lane): Update<*> {
  const update: Update<*> = {
    eventTime,
    lane,
    tag: UpdateState,
    payload: null,
    callback: null,
    next: null,
  };
  return update;
}

export function enqueueUpdate<State>(
  fiber: Fiber,
  update: Update<State>,
  lane: Lane
): FiberRoot | null {
  const updateQueue = fiber.updateQueue;
  if (updateQueue === null) {
    // Only occurs if the fiber has been unmounted.
    return null;
  }

  const sharedQueue: SharedQueue<State> = (updateQueue: any).shared;

  if (isUnsafeClassRenderPhaseUpdate(fiber)) {
    // This is an unsafe render phase update. Add directly to the update
    // queue so we can process it immediately during the current render.
    const pending = sharedQueue.pending;
    if (pending === null) {
      // This is the first update. Create a circular list.
      update.next = update;
    } else {
      update.next = pending.next;
      pending.next = update;
    }
    sharedQueue.pending = update;

    return unsafe_markUpdateLaneFromFiberToRoot(fiber, lane);
  } else {
    // return root
    return enqueueConcurrentClassUpdate(fiber, sharedQueue, update, lane);
  }
}
```

### enqueueConcurrentClassUpdate

in RootFiberConcurrentUpdates.old.js

```js
export function enqueueConcurrentClassUpdate<State>(
  fiber: Fiber,
  queue: ClassQueue<State>,
  update: ClassUpdate<State>,
  lane: Lane
) {
  const interleaved = queue.interleaved;
  if (interleaved === null) {
    // This is the first update. Create a circular list.
    update.next = update;
    // At the end of the current render, this queue's interleaved updates will
    // be transferred to the pending queue.
    pushConcurrentUpdateQueue(queue);
  } else {
    update.next = interleaved.next;
    interleaved.next = update;
  }
  queue.interleaved = update;

  return markUpdateLaneFromFiberToRoot(fiber, lane);
}
```

### lane

bitmask for scheduling
defined in ReactFiberLane.old.js

```js
export const TotalLanes = 31;

export const NoLanes: Lanes = /*                        */ 0b0000000000000000000000000000000;
export const NoLane: Lane = /*                          */ 0b0000000000000000000000000000000;
...
const TransitionHydrationLane: Lane = /*                */ 0b0000000000000000000000000100000;
const TransitionLane7: Lane = /*                        */ 0b0000000000000000001000000000000;

// use & operation to match bitmask
function getLabelForLane(lane: Lane): string | void {
    if (enableSchedulingProfiler) {
    if (lane & SyncLane) {
      return 'Sync';
    }
    if (lane & InputContinuousHydrationLane) {
      return 'InputContinuousHydration';
    }
    ...
    }
}
```
