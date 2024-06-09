import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom/client";

function App() {
  const [count, setCount] = useState(1);
  // II Render: go through the components and decide what to be updated on the DOM tree
  debugger;

  useEffect(() => {
    // IV Trigger: Restart an update life cycle by setState (-> II -> III)
    debugger;

    setCount((count) => count + 1);
  }, []);
  // III Commit: commit the update to the DOM tree
  return <button>{count}</button>;
}

// I Trigger: Entry point for the React components
ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  <App />
  // </React.StrictMode>
);
