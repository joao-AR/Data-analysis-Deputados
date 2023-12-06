// O hook é usado para pegar os dados do back-end e passar para o front 
import axios, { AxiosPromise } from "axios"
import { DeputadoData } from "../interface/DeputadoData";
import { useQuery } from "@tanstack/react-query";

const API_URL = 'http://localhost:8080';

const fatchData = async (): AxiosPromise<DeputadoData[]>=> {
    const response = axios.get(API_URL + '/deputados');
    console.log("response = \n");
    console.log(response)
    return response;
}
export function useDeputadoData(){
    const query = useQuery({
        queryFn : fatchData,
        queryKey:['deputado-data'],
        retry:2
    })

    return{
        ...query, // retorna tudo que tem dentro de query
        data: query.data?.data
    }
}