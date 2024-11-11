package com.layasree.ExtractExcelData.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_data")
public class EmployeeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated primary key for database purposes

    private String name;
    private Integer uid;
    private String location;
    private Date doj;  // Date of Joining
    private String timeWithEpam;
    private String title;
    private String status;
    private String productionCategory;
    private String jobFunction;
    private String resourceManager;
    private String pgm;  // Program Manager as of Dec 1, 2020
    private String projectCode;
    private String jfLevel;
    @Column(name = "competancy/practice")// Job function level
    private String competencyPractice;
    private String primarySkill;
    private String nicheSkills;
    @Column(name = "niche_skills(Yes/No)")
    private String nicheSkillYesNo;
    private String talentProfile2020;
    @Column(name="delivery_feedback_itscore(35%)")
    private Double deliveryFeedbackTtScore;
    @Column(name="practice_rating(30%)")
    private Double practiceRating;
    @Column(name = "strategic_orientation")
    private Double strategicOrientation;

    @Column(name = "influence")
    private Double influence;

    @Column(name = "result_orientation")
    private Double resultOrientation;

    @Column(name = "communication")
    private Double communication;

    @Column(name = "decision_making")
    private Double decisionMaking;

    @Column(name = "ask_for_more")
    private Double askForMore;

    @Column(name = "agility")
    private Double agility;

    @Column(name = "visibility")
    private Double visibility;

    @Column(name = "feedback")
    private Double feedback;

    @Column(name = "initiative_new_and_different")
    private Double initiativeNewAndDifferent;

    @Column(name = "developing_org_capabilities")
    private Double developingOrgCapabilities;

    @Column(name = "stay")
    private Double stay;

    @Column(name = "personal_connect")
    private Double personalConnect;

    @Column(name = "excellence")
    private Double excellence;

    @Column(name = "say")
    private Double say;
    @Column(name = "contribution_engxculture(10%)")
    private String contributionEngXCulture;
    @Column(name = "Contribution_extra_miles(5%)")
    private String contributionExtraMiles;
    @Column(name = "culture_score_from_feedback(20%)")
    private Double cultureScore;
    @Column(name = "overall_weighted_score(100%)")// Assuming score is numeric
    private Double overallWeightedScoreForMerit;  // Assuming score is numeric
    private String ranking;
    private Double percentile;  // Assuming percentile is numeric
    private String hrbpMapping;
    private String dh;  // Department head or similar field



}
