/**
 * This is an example of an entity, which is in a relationship with other
 * entities.
 */
@Entity
public class EntityExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    private String firstname;
    private String lastname;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "research_group_id")
    @JsonIgnoreProperties("research_group_id")
    private ResearchGroup researchGroup;

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvisorTopic> advisorTopics = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private LoginAccount loginAccount;

    public Advisor(String firstname, String lastname, String email, ResearchGroup researchGroup, LoginAccount loginAccount, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.researchGroup = researchGroup;
        this.loginAccount = loginAccount;
    }

    public Advisor() {
    }

    public Advisor(String firstname, String lastname, String email, ResearchGroup researchGroup, LoginAccount loginAccount)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.researchGroup = researchGroup;
        this.loginAccount = loginAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResearchGroup getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(ResearchGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public List<AdvisorTopic> getAdvisorTopics() {
        return advisorTopics;
    }

    public void setAdvisorTopics(List<AdvisorTopic> advisorTopics) {
        this.advisorTopics = advisorTopics;
    }

    public LoginAccount getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginAccount loginAccount) {
        this.loginAccount = loginAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Advisor))
            return false;
        Advisor advisor = (Advisor) o;
        return getId().equals(advisor.getId()) && getFirstname().equals(advisor.getFirstname())
                && getLastname().equals(advisor.getLastname()) && getEmail().equals(advisor.getEmail())
                && getResearchGroup().equals(advisor.getResearchGroup())
                && getAdvisorTopics().equals(advisor.getAdvisorTopics())
                && getLoginAccount().equals(advisor.getLoginAccount());
    }
}