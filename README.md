# TorneoDeFrescas - TP LAB 5

## ¿Que es Maven?
[Maven](https://maven.apache.org/) es una palabra escrita en el idioma yidis que significa "Acumulador de conocimiento" creada por Jason van Zyl en 2002, el origen de su uso fue buscar una forma estandar de construccion de proyectos y publicar informacion del mismo y una forma de compartir JARs facilmente. Hoy en dia, es una herramienta poderosa para utilizar, construir y administrar cualquier proyecto basado en Java. Maven gestiona, maneja las dependencias, compila, empaqueta y ejecuta los test.

## POM: ¿Que significa?¿Para que nos sirve?
POM (Project Object Model) es un archivo XML que representa todo el proyecto Maven, este proyecto contiene archivos de configuracion, version, propiedades, desarrolladores involucrados y sus roles,la organizacion y las licencias, dependencias y cada una de las pequeñas piezas necesarias para dar vida al codigo.

**Estructura XML de un archivo pom.xml**

``` xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                          http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
     
      <!-- The Basics -->
      <groupId>...</groupId>
      <artifactId>...</artifactId>
      <version>...</version>
      <packaging>...</packaging>
      <dependencies>...</dependencies>
      <parent>...</parent>
      <dependencyManagement>...</dependencyManagement>
      <modules>...</modules>
      <properties>...</properties>
     
      <!-- Build Settings -->
      <build>...</build>
      <reporting>...</reporting>
     
      <!-- More Project Information -->
      <name>...</name>
      <description>...</description>
      <url>...</url>
      <inceptionYear>...</inceptionYear>
      <licenses>...</licenses>
      <organization>...</organization>
      <developers>...</developers>
      <contributors>...</contributors>
     
      <!-- Environment Settings -->
      <issueManagement>...</issueManagement>
      <ciManagement>...</ciManagement>
      <mailingLists>...</mailingLists>
      <scm>...</scm>
      <prerequisites>...</prerequisites>
      <repositories>...</repositories>
      <pluginRepositories>...</pluginRepositories>
      <distributionManagement>...</distributionManagement>
      <profiles>...</profiles>
    </project>
```
## Diferencia entre Archetype y ArficatId
* **Archetype**: En resumen, Archetype es un conjunto de herramientas para la creación de plantillas que se pueden utilizar para crear proyectos, módulos, etc. que se basan en parámetros establecidos en la definición del arquetipo en los cuales los programadores utilizan como base para escribir y organizar el código de la aplicación. 

* **ArtifactId**: es el nombre que queramos dar al proyecto. Maven creará un directorio con este nombre y el jar que genere para el proyecto tendrá también este nombre.

## Goals: [clean,package,install,compile]

Un Goal es un comando que usa Maven como parametro:
```
mvn plugin:comando
```

* **clean**: Intenta limpiar los archivos y directorios generados por Maven durante su compilación. 
* **package**: Empaqueta el proyecto (si es un proyecto java simple, genera un jar, si es un proyecto web, un war, etc…)
* **install**: se utiliza durante la fase de instalación para agregar artefactos al repositorio local. El complemento de instalación utiliza la información en el POM (groupId, artifactId, version) para determinar la ubicación correcta para el artefacto dentro del repositorio local.
* **compile**: compila el proyecto

## Scopes: [compile,provide,runtime,test,system]

El scope sirve para indicar el alcance de nuestra dependencia y su transitividad:

* **compile**: Es la que tenemos por defecto sino especificamos scope. Indica que la dependencia es necesaria para compilar. La dependencia además se propaga en los proyectos dependientes.
* **provide**: Es como la anterior, pero esperas que el contenedor ya tenga esa libreria. Un claro ejemplo es cuando desplegamos en un servidor de aplicaciones, que por defecto, tiene bastantes librerías que utilizaremos en el proyecto, así que no necesitamos desplegar la dependencia.
* **runtime**: La dependencia es necesaria en tiempo de ejecución pero no es necesaria para compilar.
* **test**: La dependencia es solo para testing que es una de las fases de compilación con maven. JUnit es un claro ejemplo de esto.
* **system**: Es como provided pero tienes que incluir la dependencia explicitamente. Maven no buscará este artefacto en tu repositorio local. Habrá que especificar la ruta de la dependencia mediante la etiqueta <systemPath>
