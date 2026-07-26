import type { ReactNode } from "react";

export default function LayoutPanel(props: {
    children: ReactNode
}) {
    return <div className="layout-panel">
        {props.children}
    </div>
}