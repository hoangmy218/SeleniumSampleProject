// import { defineConfig } from ".allure/";
import {env} from "node:process";

export default {
    name: env.REPORT_NAME || "Allure Report",
    output: "./allure-report",
    historyPath: "./history.jsonl",
    appendHistory: true,
    variables: {
        "App Version": "2.2.6",            // Shown everywhere
        "Build Number": "#1031",         // Shown everywhere
        "Database": "stag-db-primary"      // Shown, unless overridden
    },
    flakyDetection: {
        historyDepth: 20,
        stabilizationPeriod: 3,
        includePassedTests: true,
    },
    categories: {
            rules: [
                {
                    name: "Critical regressions by layer",
                    id: "critical-regressions-by-layer",
                    matchers: {
                        statuses: ["failed", "broken"],
                        flaky: true,
                        labels: {
                            severity: "critical"
                        }
                    },
                    groupBy: ["layer", "owner", "status"],
                    groupByMessage: true,
                    groupEnvironments: true,
                    expand: true,
                    hide: false
                }
            ]
        },
    resolutions: {
        links: {
            jira: {
                urlTemplate: "https://jira.example.com/browse/%s",
                nameTemplate: "JIRA %s"
            }
        },
        rules: [
            {
                resolution: "issue",
                issue: {
                    id: "SHOP-42",
                    type: "jira"
                },
                messageRegexp: "Message text",
                comment: "Mismatch UI Text"
            }
        ]
    },
    environments: {
        dev: {
            name: "dev",
            matcher: ({ labels }) => labels.some(({ name, value }) => name === "env" && value === "dev")
        },
        staging: {
            name: "Staging",
            matcher: ({ labels }) => labels.some(({ name, value }) => name === "env" && value === "staging")
        }
    },
};