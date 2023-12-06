import { Barchart } from "../components/BarChar";
import { DespesaDeputado } from "../components/DespesasDeputados";
import Navbar from "../components/navBar";
import { useDespesaDeputadoData } from "../hooks/DespesaDeputadoDataHook";


export default function Despesas(){
    const {data} = useDespesaDeputadoData();

    return(
        <div>
            <Navbar/>
            <h1>Depesas</h1>
            <Barchart/>
            < div>
                {
                data?.map(
                deputadoData => 
                    <DespesaDeputado 
                    idDeputado={deputadoData.idDeputado} 
                    nome={deputadoData.nome} 
                    idPartido={deputadoData.idPartido} 
                    siglaPartido={deputadoData.siglaPartido} 
                    qtd={deputadoData.qtd}
                    totalGasto={deputadoData.totalGasto}
                    />
                    )
                }
            </div>
                
            
        </div>
    )
}