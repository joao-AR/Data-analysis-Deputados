import React from "react"
import { Bar } from "react-chartjs-2"
import { Chart as ChartJs, BarElement, CategoryScale, LinearScale } from "chart.js"

ChartJs.register(
    CategoryScale,
    LinearScale,
    BarElement
)

interface BarchartProps {
    numeros: number[];
    labels: string[];
}

export function Barchart(props:BarchartProps){
    
    var data = {
        labels: props.labels,
        datasets: [{
            label: '# of Votes',
            data: props.numeros,
            borderWidth: 1,
            backgroundColor:[
                'rgba(50, 168, 113)',
                '#FFF',
                '#AAA',
                '#DDD',
            ]
        }]
    }

    var options =  {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        legend:{
            labels:{
                fontSize: 36
            }
        }
    }

    return(
        <Bar data={data} height={400} options={options}/>
    )
}