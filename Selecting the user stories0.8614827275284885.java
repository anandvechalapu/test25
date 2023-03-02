
Generated Spring Boot application with controller, service and repository classes:
Controller class:
@RestController
public class SelectUserStoriesController {
    @Autowired
    private SelectUserStoriesService selectUserStoriesService;

    @GetMapping("/select-user-stories")
    public ResponseEntity<List<Projects>> getUserStories(){
        List<Projects> projects = selectUserStoriesService.getProjects();
        return ResponseEntity.ok(projects);
    }
}

Service class:
@Service
public class SelectUserStoriesService {
    @Autowired
    private SelectUserStoriesRepository selectUserStoriesRepository;

    public List<Projects> getProjects(){
        return selectUserStoriesRepository.getProjects();
    }
}

Repository class:
@Repository
public class SelectUserStoriesRepository {
    @Autowired
    private JiraEndpoint jiraEndpoint;

    public List<Projects> getProjects(){
        List<Projects> projects = jiraEndpoint.getProjects();
        return projects;
    }
}