# SD_TP1

Repositório dedicado apenas à cadeira sistemas distribuídos do curso de engenharia informática da UBI.

# TestePratico

O objetivo é criar uma aplicação que permita gerir a partilha de recursos entre uma população. Por exemplo, alguém disponibiliza um livro que poderá ser requisitado por outros e devolvido após uso. Para isso queremos uma aplicação cliente/servidor em java RMI, em que no processo servidor um objeto remoto disponibilizará as seguintes operações:

Op1 - Inserir livros. O processo cliente fornece ao servidor os dados do recurso que disponibiliza.

Op2 – Consultar livros. O processo cliente fornece um valor do tipo String, o método devolve todos os recursos que na sua descrição contêm essa String.

Op3 – Requisitar/Reservar um livro. O cliente envia o identificador do recurso que pretende, e envia também o endereço de um objeto para o qual o servidor poderá fazer um callbak. Se o recurso estiver disponível, o recurso fica assinalado no servidor como passando a estar “requisitado”. Se o recurso já estiver atribuído a um cliente, passará ao estado “reservado”. O cliente deve receber como resultado uma String, com o estado do recurso. Quando o recurso for devolvido, o cliente que o reservou deve receber um callback a avisar que o recurso já está disponível. Após o callback, o recurso passará para o estado “requisitado”.

Op4 – Devolver um livro. Um utilizador devolve um recurso que requisitou. O recurso passa ao estado “disponível”.

Op5 – Listar livros. O servidor envia ao cliente uma lista de todos os recursos disponíveis.
