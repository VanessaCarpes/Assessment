package com.job.application.service;

import com.job.application.dto.JobTitle;
import java.text.Normalizer;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class Normaliser {
    private final List<JobTitle> jobTitles = Arrays.asList(
        new JobTitle("Accountant", Arrays.asList(Normalizer.normalize("accountant", Normalizer.Form.NFD))),
        new JobTitle("Architect", Arrays.asList(Normalizer.normalize("architect", Normalizer.Form.NFD))),
        new JobTitle(
            "Software engineer",
            Arrays.asList(
                Normalizer.normalize("software", Normalizer.Form.NFD),
                Normalizer.normalize("engineer", Normalizer.Form.NFD)
            )
        ),
        new JobTitle(
            "Quantity surveyor",
            Arrays.asList(
                Normalizer.normalize("quantity", Normalizer.Form.NFD),
                Normalizer.normalize("surveyor", Normalizer.Form.NFD)
            )
        )
    );

    public String normalise(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        List<String> keywords = Arrays.asList(normalizedText.split(" "));
        int totalOfWords = keywords.size();

        Set<JobTitle> matches = new HashSet();

        for (String keyword : keywords) {
            jobTitles.forEach(
                jobTitle -> {
                    if (jobTitle.getComposition().stream().anyMatch(el -> keyword.trim().toLowerCase().contains(el))) {
                        jobTitle.setMatch(jobTitle.getMatch() + 1);
                        jobTitle.setTotalMatch(totalOfWords);
                        matches.add(jobTitle);
                    }
                }
            );
        }

        if (matches.size() == 0) {
            return null;
        }

        return matches
            .stream()
            .sorted(Comparator.comparing(JobTitle::getMatches).reversed())
            .findFirst()
            .get()
            .getName();
    }
}
