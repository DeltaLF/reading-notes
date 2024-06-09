import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom/client";

function App() {
  const [count, setCount] = useState(1);
  debugger;

  useEffect(() => {
    debugger;

    setCount((count) => count + 1);
  }, []);
  return <button>{count}</button>;
}

ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  <App />
  // </React.StrictMode>
);
