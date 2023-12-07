// O hook é usado para pegar os dados do back-end e passar para o front 
import axios, { AxiosPromise } from "axios"

import { useQuery } from "@tanstack/react-query";
import { ProposicoesDeputadoData } from "../interface/ProposicoesDeputadoData";



const API_URL = 'http://localhost:8080';

const fatchData = async (): AxiosPromise<ProposicoesDeputadoData[]>=> {
    const response = axios.get(API_URL + '/proposicao-deputado');
    return response;
}
export function useProposicaoDeputadoData(){
    const query = useQuery({
        queryFn : fatchData,
        queryKey:['proposicao-deputado-data'],
        retry:2
    })

    return{
        ...query, // retorna tudo que tem dentro de query
        data: query.data?.data
    }
}