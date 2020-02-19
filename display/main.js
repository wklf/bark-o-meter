
let data_display = document.getElementById('data_display');

function loadData(){
    let data = $.ajax({
        url: "http://localhost:5000/data/week",
        type: "GET",
        headers: {"Accept": "application/json"},
    }).fail(function(){
        console.log('error');
    });
    return data;
}

function display(){
    loadData().then(function(rawData){
        console.log(rawData);
        let dataX = [];
        let dataY = [];
        let dataOnX = [];
        let dataOnY = [];
        let dataOffX = [];
        let dataOffY = [];

        for(i = 0; i < rawData.length; i++){
            let temp = rawData[i];

            if(temp.event == 'event'){
                dataX.push(convertIntTimeToStrTimestamp(temp.time));
                dataY.push(temp.level);
            } else if(temp.event == 'on'){
                dataOnX.push(convertIntTimeToStrTimestamp(temp.time));
                dataOnY.push(temp.level);
            } else if(temp.event == 'off'){
                dataOffX.push(convertIntTimeToStrTimestamp(temp.time));
                dataOffY.push(temp.level);
            }
        }

        plotData(dataX, dataY, dataOnX, dataOnY, dataOffX, dataOffY);
    });
}

function convertIntTimeToStrTimestamp(time){
    time = new Date(time);
    let result = time.getFullYear() + "-" +
        (time.getMonth() + 1) + "-" +
        time.getDate() + " " +
        time.getHours() + ":" +
        time.getMinutes() + ":" +
        time.getSeconds();
    return result;
}

function plotData(dataEventX, dataEventY, dataOnX, dataOnY, dataOffX, dataOffY){

    var data = [
        {
            name: "Event",
            type: "scatter",
            mode: "markers",
            marker: {
                color: 'rgba(63,182,191,0.4)',
                size: 20,
                    line: {
                        color: '#873FBF',
                        width: 3
                    }
            },
            x: dataEventX,
            y: dataEventY
        },
        {
            name: "On",
            mode: "markers",
            marker: {
                color: '#0e7d07',
                size: 20
            },
            x: dataOnX,
            y: dataOnY
        },
        {
            name: "Off",
            mode: "markers",
            marker: {
                color: '#ad2800',
                size: 20
            },
            x: dataOffX,
            y: dataOffY
         }
    ];

    var layout = {
        title: 'Recent Activity',
        xaxis: {
            type: 'date'
        },
        yaxis: {
            type: 'linear',
            range: [-0.5, 6],
            zeroline: false
        },
        showlegend: true,
        legend: {
            x: 0.7,
            y: 1.15,
            orientation: "h",
            bordercolor: '#000000',
            borderwidth: 1
        } 
    };
    
    Plotly.newPlot('data_display', data, layout);
}

