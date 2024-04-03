<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <h1>${movie.name}</h1>
        <p>${movie.releaseYear}</p>
        <h2>${movie.director.fullName}</h2>
        <p>${movie.description}</p>
    </div>
</@main.layout>