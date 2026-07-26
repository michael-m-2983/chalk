import LayoutPanel from "./components/layout/LayoutPanel";
import LayoutShell from "./components/layout/LayoutShell";
import LayoutSidebar from "./components/layout/LayoutSidebar";
import "./style.css"

export default function App() {
  return <LayoutShell>
    <LayoutSidebar>
      sidebar
    </LayoutSidebar>
    <LayoutPanel>
      main
    </LayoutPanel>
  </LayoutShell>
}