<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="m-5">
        <a href="/add" class="btn btn-primary">Create new movie</a>
        <a href="/api/movies" class="btn btn-primary">JavaScript Movies</a>
        <div class="row">
            <#list movies as movie>
                <div class="col m-2">
                    <div class="card" style="width: 18rem; min-height: 200px">
                        <#--        <img src="..." class="card-img-top" alt="...">-->
                        <div class="card-header">
                            <h5 class="card-title">${movie.name}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${movie.description}</p>
                            <a href="/info/${movie.id}" class="btn btn-primary">More info</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@main.layout>