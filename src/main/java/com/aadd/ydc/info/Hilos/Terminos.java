package com.aadd.ydc.info.Hilos;

/////////////////////////////////////////////////// EXPLICACIÓN DE TÉRMINOS /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//🔹 Concurrencia vs Paralelismo:
//- Concurrencia: Varios procesos pueden ejecutarse al mismo tiempo, pero no necesariamente simultáneamente.
//- Paralelismo: Tareas realmente ejecutadas en paralelo (solo en CPUs con múltiples núcleos).

//🔹 ConcurrentCollections:
//- Son estructuras seguras para múltiples hilos: ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue, etc.

//🔹 synchronized:
//- Bloquea un método o sección de código para que solo un hilo pueda ejecutarlo a la vez.

//🔹 volatile:
//- Indica que una variable puede ser modificada por varios hilos y siempre debe ser leída desde la memoria principal.

//🔹 static:
//- Variables estáticas son compartidas entre todas las instancias de la clase, lo que puede generar problemas de concurrencia.

//🔹 ExecutorService y CompletableFuture:
//- ExecutorService administra un grupo de hilos reutilizables.
//- CompletableFuture permite programación asíncrona sin bloqueos.

//🔹 wait() y notify():
//- wait(): Hace que un hilo espere hasta que otro lo despierte.
//- notify(): Despierta un hilo que esté esperando en el mismo monitor.

//⚠️ Advertencias:
//- Evita condiciones de carrera con synchronized o concurrent collections.
//- Usa volatile solo cuando no necesitas operaciones atómicas.
//- ExecutorService debe cerrarse con shutdown().
//- wait() y notify() deben usarse dentro de bloques sincronizados.
