package tech.murilo.githubuserrepositories.main;

import tech.murilo.githubuserrepositories.service.GitHubRepository;
import tech.murilo.githubuserrepositories.service.GitHubRepositoryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0].isEmpty()) {
            System.out.println("Entre com o usuário!");
            return;
        }

        String user = args[0];

        GitHubRepositoryService service = new GitHubRepositoryService();
        List<GitHubRepository> repositories = service.listRepositoriesFromUser(user);

        if (repositories.isEmpty()) {
            System.out.println("Não foram encontrados repositórios para o usuário: " + user);
            return;
        }

        System.out.println("Repositórios de " + user +":");
        System.out.println("");

        for (GitHubRepository gitHubRepository: repositories) {
            System.out.print("Nome: " + gitHubRepository.getName() + ", ");
            System.out.print("Forks: " + gitHubRepository.getForksCount() + ", ");
            System.out.println("Estrelas: " + gitHubRepository.getStarsCount());
        }
    }
}
