interface DespesasDeputadosProps{
    idDeputado: number,
    nome: string,
    idPartido: number,
    siglaPartido:string,
    qtd: number,
    totalGasto: number,
}

export function DespesaDeputado(props : DespesasDeputadosProps){
    
    return(
        <div>
            <h2>Despesas Deputados</h2>
            <div>{props.nome}</div>
            <div>{props.qtd}</div>
            <div>{props.totalGasto}</div>
        </div>
    )
}