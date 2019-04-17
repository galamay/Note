<#import "parts/comon.ftl" as c>

<@c.page>
    <div class="form-row" xmlns="http://www.w3.org/1999/html">
        <div class="form-group col-ml-8">
            <form method="get" action="/note" class="form-inline">
                <input type="text" name="filterHeadline" class="form-control" value="${filterHeadline?ifExists}"
                       placeholder="Поиск по заголовку"/>
                <button type="submit" class="btn btn-primary ml-2">Поиск</button>
            </form>
        </div>
        <div class="form-group col-ml-8">
            <form method="get" action="/note" class="form-inline">
                <input type="text" name="filterNoteText" class="form-control" value="${filterNoteText?ifExists}"
                       placeholder="Поиск по тексту"/>
                <button type="submit" class="btn btn-primary ml-2">Поиск</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новую заметку
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="headline" maxlength="100"
                           placeholder="Введите заголовок (не более 100 символов)"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="noteText" required="true" maxlength="1000"
                           placeholder="Текст заметки (не более 1000 символов)"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns" id="note-list">
        <#list notes as note>
            <div class="card my-3" id="${note.id}">
                <div class="m-2">
                    <div class="card-header">
                        <i>
                            ${note.headline}
                            <button type="button" class="close" aria-label="Close">
                                <span aria-hidden="true"><a href="/del/${note.id}">&#10008;</a></span>
                            </button>
                        </i>
                    </div>
                    <div class="card-body">
                        <span>${note.noteText}</span>
                    </div>
                </div>
            </div>
        <#else>
            Нет заметок
        </#list>
    </div>
</@c.page>