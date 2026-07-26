import type { ReactElement } from "react";

export default function LayoutShell(props: {
    children: [ReactElement, ReactElement]
}) {
    return <div className="layout-shell">
        {props.children}
    </div>
}