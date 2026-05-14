import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import AnimalPage from './pages/AnimalPages.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AnimalPage />
  </StrictMode>,
)
