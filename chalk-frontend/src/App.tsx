import Editor from "./components/Editor";
import LayoutPanel from "./components/layout/LayoutPanel";
import LayoutShell from "./components/layout/LayoutShell";
import LayoutSidebar from "./components/layout/LayoutSidebar";
import "./style.css";

function Bubble(props: {
  text: string
}) {
  return <div style={{ padding: '2rem', margin: '2rem', background: 'radial-gradient(#fafafa, #dadada)', borderRadius: '7px' }} spellCheck={false}>
    <Editor defaultValue={props.text} onChange={() => {}} />
  </div>
}

export default function App() {
  return <LayoutShell>
    <LayoutSidebar>
      {["Recent", "People", "Places", "etc."].map(label => <p>{label}</p>)}
    </LayoutSidebar>
    <LayoutPanel>
      <h1 style={{ marginLeft: '3rem' }}>Recent Notes</h1>
      <Bubble text={"# Welcome to Chalk!\n\n***\n\nThis is a demo of the frontend."} />
      <Bubble text={"Try pressing `CTRL-SHIFT-B` to expand the left sidebar."} />
      <Bubble text={'## Markdown features demo\n\n***\n\n> "That’s one small step for a man, one giant leap for mankind."\n>\n> \- Neil Armstrong\n\n* This is an unordered list.\n\n  * With nesting\n\n1. This is an ordered list\n2. With multiple items\n\n<br />\n\n* [x] Add task lists\n* [ ] Integrate frontend and backend\n* [ ] Add image and file upload support\n* [ ] Improve logging\n* [ ] Add REST API Documentation\n* [ ] Add docker support\n\n<br />\n\n```C\n#include <stdio.h>\n#include <stdlib.h>\n#include <unistd.h>\n#include <string.h>\n\n#define MAX_COMMAND_LENGTH 20\n\nint main(void) {\n    fputs("Connecting to database...\\n", stdout);\n    sleep(1);\n\n    char command[MAX_COMMAND_LENGTH];\n\n    while(1) {\n        fputs("> ", stdout);\n        fgets(command, MAX_COMMAND_LENGTH, stdin);\n        command[strcspn(command, "\\n")] = 0;\n\n        if(strncmp("exit", command, 4) == 0) {\n            break;\n        } else if(strncmp("run", command, 3) == 0) {\n            fputs("Deploying", stdout);\n            fflush(stdout);\n            sleep(1);\n            fputs(".", stdout);\n            fflush(stdout);\n            sleep(1);\n            fputs(".", stdout);\n            fflush(stdout);\n            sleep(1);\n            fputs(".", stdout);\n            fflush(stdout);\n            sleep(1);\n            puts("");\n            puts("Deployed!");\n        } else {\n            printf("unknown command: %s\\n", command);\n        }\n    }\n\n    return 0;\n}\n```\n\nYou can run this with `gcc -std=c99 -Wall -Werror ./file.c -o file && ./file`.\n\n\n$$\n\\nabla f = \\left\\lang {\\partial f \\over \\partial x}, {\\partial f \\over \\partial y}, {\\partial f \\over \\partial z} \\right\\rang\n$$\n\n| Item           | Price | Details                         |\n| :------------- | :---- | :------------------------------ |\n| Server hosting | \\$X/m | VPS @ DigitalOcean or something |\n| Domain name    | \\$Y/m | Namecheap                       |\n'} />
    </LayoutPanel>
  </LayoutShell>
}