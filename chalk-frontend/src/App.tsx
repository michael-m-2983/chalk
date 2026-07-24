import { useEffect, useState } from "react";
import { Editor } from "./components/Editor";

/**
 * Basic API test component
 */
function APITest() {
  let [str, setStr] = useState<string>("my_string");
  let [len, setLen] = useState<number | undefined>(undefined);

  let textbox = <input type="text" value={str} onChange={(e) => setStr(e.currentTarget.value)}/>

  useEffect(() => {
    fetch(`/api/length/${str}`).then(r => r.text()).then(text => {
      setLen(
        parseInt(
          text.replaceAll(/[^0-9]+/g, "")
        )
      );
    });
  }, [str, setLen]);

  return <p>Length of "{textbox}" is {len || "not defined"}.</p>
}

export default function App() {
  return <div>
    <APITest />
    <Editor />
  </div>
}