// O hook é usado para pegar os dados do back-end e passar para o front 
import axios, { AxiosPromise } from "axios"

import { useQuery } from "@tanstack/react-query";
import { ProposicoesPartidoData } from "../interface/ProposicoesPartidoData";



const API_URL = 'http://localhost:8080';

const fatchData = async (): AxiosPromise<ProposicoesPartidoData[]>=> {
    const response = axios.get(API_URL + '/proposicao-partido');
    return response;
}
export function useProposicaoPartidoData(){
    const query = useQuery({
        queryFn : fatchData,
        queryKey:['proposicao-partido-data'],
        retry:2
    })

    return{
        ...query, // retorna tudo que tem dentro de query
        data: query.data?.data
    }
}