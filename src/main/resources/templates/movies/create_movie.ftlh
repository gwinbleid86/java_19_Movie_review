<#import "../layout.ftlh" as main>
<@main.layout>
    <form action="/add" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Movie name</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="mb-3">
            <label for="release_year" class="form-label">Release year</label>
            <input type="number" class="form-control" id="release_year" name="releaseYear">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description">
        </div>
        <div class="mb-3">
            <label for="directorName" class="form-label">Director name</label>
            <input type="text" class="form-control" id="directorName" name="directorName">
        </div>
        <div class="mb-3">
            <label for="castMemberName" class="form-label">Cast name</label>
            <input type="text" class="form-control" id="castMemberName" name="castMemberName">
        </div>
        <div class="mb-3">
            <label for="castMemberRole" class="form-label">Role</label>
            <input type="text" class="form-control" id="castMemberRole" name="castMemberRole">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</@main.layout>