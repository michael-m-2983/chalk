import { useEffect } from "react";

/**
 * Hook for adding a keyboard shortcut
 * 
 * @param action Called when the shortcut is used
 * @param key The key
 * @param ctrl Does the CTRL key need to be pressed?
 */
export function useKeyboardShortcut(action: () => any, key: string, ctrl: boolean = false) {
    useEffect(() => {
        const onKeyDown = (e: KeyboardEvent) => {
            if(ctrl && !e.ctrlKey) return;
            if(e.key.toLowerCase() != key.toLowerCase()) return;

            e.preventDefault();
            action();
        };

        window.addEventListener("keydown", onKeyDown);
        return () => window.removeEventListener("keydown", onKeyDown);
    }, [action, key, ctrl]);
}