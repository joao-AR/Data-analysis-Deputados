// O hook é usado para pegar os dados do back-end e passar para o front 
import axios, { AxiosPromise } from "axios"

import { useQuery } from "@tanstack/react-query";
import { DespesasDeputadosData } from "../interface/DespesasDeputadoData";

const API_URL = 'http://localhost:8080';

const fatchData = async (): AxiosPromise<DespesasDeputadosData[]>=> {
    const response = axios.get(API_URL + '/despesas-deputado');
    console.log("response = \n");
    console.log(response)
    return response;
}
export function useDespesaDeputadoData(){
    const query = useQuery({
        queryFn : fatchData,
        queryKey:['despesa-deputado-data'],
        retry:2
    })

    return{
        ...query, // retorna tudo que tem dentro de query
        data: query.data?.data
    }
}