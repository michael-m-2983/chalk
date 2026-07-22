import { Crepe } from '@milkdown/crepe'
import { Milkdown, MilkdownProvider, useEditor } from '@milkdown/react'
import '@milkdown/crepe/theme/common/style.css'
import '@milkdown/crepe/theme/frame.css'

const CrepeEditor: React.FC = () => {
  useEditor((root) => {
    return new Crepe({ root })
  })

  return <Milkdown />
}

export const Editor: React.FC = () => {
  return (
    <MilkdownProvider>
      <CrepeEditor />
    </MilkdownProvider>
  )
}