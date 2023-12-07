import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import Home from './routes/Home.tsx'


import {
  createBrowserRouter, RouterProvider
} from "react-router-dom";
import Despesas from './routes/Despesas.tsx'
import Proposicoes from './routes/Proposicoes.tsx'

const router = createBrowserRouter([
  {
    path:"/",
    element: <Home/>
  },
  {
    path:"despesas",
    element: <Despesas/>
  },
  {
    path:"proposicoes",
    element: <Proposicoes/>  
  }
  
]);

const queryClient = new QueryClient();
ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
      <QueryClientProvider client={queryClient}>
        <RouterProvider router={router}/>
      </QueryClientProvider>
  </React.StrictMode>,
)
