<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <h1>${movie.name}</h1>
        <img src="https://www.shutterstock.com/image-photo/british-shorthair-kitten-silver-color-260nw-1510641710.jpg"
             alt="${movie.name}">
        <p>${movie.releaseYear}</p>
        <h2>${movie.director.fullName}</h2>
        <p>${movie.description}</p>


        <style>
            .box {
                border: solid 1px black;
                background: rgba(150, 250, 20, 0.33);
                display: inline-block;
                padding: 25px;
                margin: 0;
            }

            .box:hover {
                cursor: pointer;
            }
        </style>
        <div class="container">
            <div class="box" onclick="boxClick(event)" data-counter="counter_1"></div>
            <p class="box" onclick="boxClick(event)" data-counter="counter_2"></p>
            <span class="box" onclick="boxClick(event)" data-counter="counter_3"></span>

            <p class="counter" id="counter_1">0</p>
            <p class="counter" id="counter_2">0</p>
            <p class="counter" id="counter_3">0</p>
        </div>
        <button id="button" type="button" class="btn btn-danger">Danger</button>
    </div>

    <img src="" alt="">

    <script src="/static/js/script.js"></script>
</@main.layout>