import React from "react"
import { Bar } from "react-chartjs-2"
import { Chart as ChartJs, BarElement, CategoryScale, LinearScale } from "chart.js"

ChartJs.register(
    CategoryScale,
    LinearScale,
    BarElement
)

export function Barchart({}){
    var data = {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
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