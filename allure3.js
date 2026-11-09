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
        "windows-chrome": {
            matcher: ({labels}) =>
                labels.some(l => l.name === "os" && l.value === "Windows") &&
                labels.some(l => l.name === "browser" && l.value === "Chrome"),
            variables: {
                "Browser": "Chrome",
                "OS": "Windows",
            }
        },
        "windows-firefox": {
            matcher: ({labels}) =>
                labels.some(l => l.name === "os" && l.value === "Windows") &&
                labels.some(l => l.name === "browser" && l.value === "Firefox"),
            variables: {
                "Browser": "Firefox",
                "OS": "Windows",
            }
        },
        "ubuntu-chrome": {
            matcher: ({labels}) =>
                labels.some(l => l.name === "os" && l.value === "Ubuntu") &&
                labels.some(l => l.name === "browser" && l.value === "Chrome"),
            variables: {
                "Browser": "Chrome",
                "OS": "Ubuntu",
            }
        },
        "ubuntu-firefox": {
            matcher: ({labels}) =>
                labels.some(l => l.name === "os" && l.value === "Ubuntu") &&
                labels.some(l => l.name === "browser" && l.value === "Firefox"),
            variables: {
                "Browser": "Firefox",
                "OS": "Ubuntu",
            }
        }
    }
};