import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: "./",
  server: {
    // It would be bad to leave this enabled in production
    // But it is served as static HTML, so it is never on in production
    allowedHosts: true,
    host: true
  }
})
