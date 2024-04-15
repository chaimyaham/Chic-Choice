pipeline {
    agent any

    tools {
        // Installe la version de Maven configurée sous le nom "M3" et l'ajoute au chemin d'accès.
        maven "Maven"
    }

    stages {
        stage('Build') {
            steps {
                // Récupérer du code depuis un dépôt GitHub
                git 'https://github.com/chaimyaham/Chic-Choice-backend.git'

                // Exécuter Maven sur un agent Unix.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Test') {
            steps {
                // Récupérer du code depuis un dépôt GitHub
                git 'https://github.com/chaimyaham/Chic-Choice-backend.git'

                // Exécuter Maven sur un agent Unix.
                sh "mvn test"
            }
        }
    }
}
