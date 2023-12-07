import React from "react"
import { Link } from "react-router-dom"
export default function Navbar(){

    return(
        <nav style={{display:'flex', flexDirection:'row', justifyContent:'space-around', width:600, fontSize:40}}>
            <Link to = "despesas">Despesas</Link>
            <Link to = "proposicoes">Proposições</Link>
        </nav>
    )
}