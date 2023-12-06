interface DeputadoProps{
    id: number,
    idPartido: number,
    nome: string,
    urlFoto: string,
    email:string,
}

export function Deputado(props : DeputadoProps){

    return(
        <div>
            <h2>Deputados</h2>
            <div>{props.nome}</div>
        </div>
    )
}