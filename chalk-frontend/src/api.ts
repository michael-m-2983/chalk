export interface NoteData {
  id: string;
  content: string;
  createdAt: string;
  updatedAt: string;
  tags: string[];
}

export async function listNotes(): Promise<string[]> {
  const res = await fetch("/api/note");
  const data = await res.json();
  return data.ids;
}

export async function getNote(id: string): Promise<NoteData> {
  const res = await fetch(`/api/note/${encodeURIComponent(id)}`);
  const data = await res.json();
  return data.note;
}

export async function createNote(content: string): Promise<string> {
  console.log("creating: " + content);
  const res = await fetch("/api/note", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ content: content }),
  });
  const data = await res.json();
  return data.id;
}
