import { useKeyboardShortcut } from "@/hooks/shortcuts";
import { useState, type ReactNode } from "react";

export default function LayoutSidebar(props: {
    children: ReactNode
}) {
    const [open, setOpen] = useState<boolean>(false);

    // CTRL-Shift-B toggles the sidebar
    useKeyboardShortcut(() => setOpen(open => !open), "b", true, true);

    return <div className={`layout-sidebar ${open ? 'open' : 'closed'}`}>
        <Toggler toggle={() => setOpen(open => !open)} />
        <br />
        {props.children}
    </div>
}

function Toggler(props: {
    toggle: () => void
}) {
    return <button onClick={props.toggle}>#</button>
}