
let data_display = document.getElementById('data_display');

function load(){

}

function display(){

}

function load_data(){

    var data = [
        {
            name: "Sound level",
            type: "scatter",
            mode: "lines",
            line: {color: '#05a6a1'},
            x: ['2020-02-16 14:15:00',
                '2020-02-16 14:20:00',
                '2020-02-16 14:21:00',
                '2020-02-16 14:22:00',
                '2020-02-16 14:23:00',
                '2020-02-16 14:24:00',
                '2020-02-16 14:25:00',
                '2020-02-16 14:26:00',
                '2020-02-16 14:27:00',
                '2020-02-16 14:28:00',
                '2020-02-16 14:29:00',
                '2020-02-16 14:30:00',
                '2020-02-16 14:31:00',
                '2020-02-16 14:32:00',
                '2020-02-16 16:32:00',
                '2020-02-16 16:33:00',
                '2020-02-16 16:36:00',
                '2020-02-16 16:37:00',
                '2020-02-16 18:32:00',
                '2020-02-16 18:52:00'
            ],
            y: [0, 0, 0, 0, 0, 0, 1, 3, 2, 0, 3, 2, 3, 0, 0, 5, 4, 0, 0, 0]
       },
       {
           name: "On",
           mode: "markers",
           marker: {
            color: '#0e7d07',
            size: 10
           },
           x: ['2020-02-16 14:15:00'],
           y: [0],
       },
       {
        name: "Off",
        mode: "markers",
        marker: {
         color: '#ad2800',
         size: 10
        },
        x: ['2020-02-16 18:52:00'],
        y: [0]
        }
    ];

    var layout = {
        title: 'Recent Activity',
        xaxis: {
            type: 'date'
        },
        yaxis: {
            range: [-1, 5],
            type: 'linear'
        },
        showlegend: true,
        legend: {
            x: 1,
            xanchor: 'right',
            y: 1
        } 
    };
    Plotly.newPlot('data_display', data, layout);
}
