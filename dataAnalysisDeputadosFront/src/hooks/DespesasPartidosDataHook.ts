// O hook é usado para pegar os dados do back-end e passar para o front 
import axios, { AxiosPromise } from "axios"

import { useQuery } from "@tanstack/react-query";
import { DespesasPartidoData } from "../interface/DespesasPartidoData";


const API_URL = 'http://localhost:8080';

const fatchData = async (): AxiosPromise<DespesasPartidoData[]>=> {
    const response = axios.get(API_URL + '/despesas-partido');
    return response;
}
export function useDespesaPartidoData(){
    const query = useQuery({
        queryFn : fatchData,
        queryKey:['despesa-partido-data'],
        retry:2
    })

    return{
        ...query, // retorna tudo que tem dentro de query
        data: query.data?.data
    }
}