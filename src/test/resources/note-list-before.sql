delete from note;

insert into note(id, headline, note_text) values
(1, 'заголовок 1', 'текст заголовка 1'),
(2, 'снова заголовок', 'снова текст'),
(3, 'опять', 'ничего нового'),
(4, 'повторяем опять v2', 'повторяем');

alter sequence hibernate_sequence restart with 10;