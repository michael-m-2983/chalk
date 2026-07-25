import { useEffect, useState } from "react";
import { Editor } from "./components/Editor";
import { listNotes, getNote, createNote } from "./api";

interface NoteInfo {
  id: string;
  title: string;
}

type Tab = "notes" | "editor";

export default function App() {
  const [tab, setTab] = useState<Tab>("notes");
  const [notes, setNotes] = useState<NoteInfo[]>([]);
  const [content, setContent] = useState("");

  async function refreshNotes() {
    try {
      const ids = await listNotes();
      const notesWithContent = await Promise.all(
        ids.map(async (id) => {
          const note = await getNote(id);
          const title = note.content.split("\n")[0].slice(0, 60) || "(empty)";
          return { id, title };
        })
      );
      setNotes(notesWithContent);
    } catch (e) {
      console.error("Failed to fetch notes", e);
    }
  }

  useEffect(() => {
    refreshNotes();
  }, []);

  async function handleCreate() {
    if (!content.trim()) return;
    try {
      await createNote(content);
      setContent("");
      refreshNotes();
    } catch (e) {
      console.error("Failed to create note", e);
    }
  }

  return (
    <div style={{ padding: "16px" }}>
      <div style={{ marginBottom: "16px" }}>
        <button onClick={() => setTab("notes")} disabled={tab === "notes"}>
          Notes
        </button>
        <button onClick={() => setTab("editor")} disabled={tab === "editor"}>
          Editor
        </button>
      </div>

      {tab === "notes" && (
        <div>
          <h2>Notes</h2>
          {notes.length === 0 ? (
            <p>No notes yet.</p>
          ) : (
            <ul>
              {notes.map((n) => (
                <li key={n.id} style={{ marginBottom: "4px" }}>
                  {n.id} — {n.title}
                </li>
              ))}
            </ul>
          )}

          <h3>Create a new note</h3>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows={6}
            cols={60}
            placeholder="Write your note in markdown..."
          />
          <br />
          <button onClick={handleCreate}>Create</button>
        </div>
      )}

      {tab === "editor" && (
        <div>
          <h2>Editor</h2>
          <Editor />
        </div>
      )}
    </div>
  );
}
