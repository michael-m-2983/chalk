import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: "./",
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  build: {
    outDir: "../chalk-backend/src/main/resources/static",
    emptyOutDir: true
  },
  server: {
    // It would be bad to leave this enabled in production
    // But it is served as static HTML, so it is never on in production
    allowedHosts: true,
    host: true,

    proxy: {
      "/api": "http://localhost:8080"
    }
  }
})
