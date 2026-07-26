import { Crepe } from '@milkdown/crepe'
import { Milkdown, MilkdownProvider, useEditor } from '@milkdown/react'
import '@milkdown/crepe/theme/common/style.css'
import '@milkdown/crepe/theme/frame.css'
import { listenerCtx } from '@milkdown/kit/plugin/listener';
import { blockquoteKeymap } from '@milkdown/kit/preset/commonmark';

interface EditorProps {
  defaultValue?: string;
  onChange: (markdown: string) => void
}

function CrepeEditor(props: EditorProps) {
  useEditor((root) => {
    let crepe = new Crepe({
      root,
      defaultValue: props.defaultValue || ""
    });
    crepe.editor.config((context) => {

      // Call onChange when markdown updates
      context.get(listenerCtx).markdownUpdated((_, markdown) => {
        props.onChange(markdown);
      });

      // Fix conflict with CTRL-SHIFT-B
      // It is also used for toggling the sidebar
      context.set(blockquoteKeymap.key, {
        WrapInBlockquote: {
          shortcuts: ""
        }
      });
    })

    return crepe;
  })

  return <Milkdown />
}

export default function (props: EditorProps) {
  return (
    <MilkdownProvider>
      <CrepeEditor {...props} />
    </MilkdownProvider>
  )
}