# Indoor Cricket Scorer
Master Project Context

Version: 1.0
Last Updated: July 2026

---

# Project Vision

Build the BEST offline-first Indoor Cricket Scorer Android application ever made.

This project is not just another scoring app.

Goals:

- lightning fast
- one handed operation
- scorer friendly
- beautiful UI
- production quality
- portfolio quality
- scalable architecture
- eventually Play Store ready

This project is also intended to become my flagship UI/UX and Android portfolio project.

---

# Technology Stack

Android Native

Kotlin

Jetpack Compose

MVVM

Room Database

Repository Pattern

Coroutines

Flow

Navigation Compose

Material 3

Git

GitHub

Arena AI

ChatGPT

---

# Source of Truth

GitHub Repository

https://github.com/basu99/IndoorCricketScorer

Always assume GitHub contains the newest version of the project.

Never ask the user to paste entire files if the GitHub repository contains them.

Only request code snippets when absolutely necessary.

---

# Development Philosophy

Never rush by creating hacks.

Always prefer proper architecture.

Avoid technical debt.

Never duplicate code.

Always extract reusable composables.

Always refactor before complexity becomes too large.

Every sprint should improve architecture.

---

# Git Workflow

Every completed sprint:

git status

git add .

git commit -m "Sprint X - Feature"

git push origin main

GitHub is updated after every successful build.

---

# Arena AI Workflow

Arena AI is used for implementation assistance.

ChatGPT is the lead architect.

Arena follows ChatGPT instructions.

ChatGPT should always think several steps ahead.

---

# UI Philosophy

Modern

Minimal

Material 3

Fast

Scorer focused

No unnecessary taps.

Everything reachable with one hand.

Large touch targets.

Readable typography.

Dark mode friendly.

---

# Current Architecture

MVVM

Room Database

Repositories

ViewModels

Composable Screens

Reusable Components

Navigation Compose

Room persists:

Matches

Players

Scorecards

---

# Completed Features

Home Screen

Navigation

New Match

Player Entry

Player Repository

Player Search

Player Suggestions

Duplicate Player Prevention

Match Repository

Match History

Match Details

Statistics placeholder

Settings placeholder

Live Score Screen

Current Run Rate

Required Run Rate

Target calculation

Undo

Recent Balls

Bowler Card

Batter Card

Reusable Match Action Bar

Scorecard Screen

Match Summary Screen

Reset Match Confirmation

Next Batter Dialog

Opening Batter Selection Screen

Player Suggestions Dropdown

Repository architecture

ViewModel factories

Git workflow established

---

# Deferred

Indoor Cricket Rules

DO NOT implement Indoor Cricket specific rules yet.

Examples:

Zone scoring

Wall rules

Bonus runs

Indoor dismissal rules

Retirement rules

These will be implemented later in a dedicated phase.

---

# Current Priority

Live Match Polish

Reduce UI duplication

Improve scorer workflow

Improve dialogs

Improve reusable components

Improve state handling

Improve navigation flow

Improve player selection UX

Improve score entry UX

---

# Current Navigation

Home

↓

New Match

↓

Opening Batters

↓

Live Match

↓

Match Summary

↓

Scorecard

↓

Home

---

# Coding Standards

Always use:

MVVM

Repository Pattern

Compose Best Practices

No magic numbers

Meaningful names

Reusable composables

No duplicated business logic

Prefer immutable state

Use StateFlow

Use Flow

Keep ViewModels thin.

Repositories talk to Room.

Screens should not contain business logic.

---

# Component Strategy

Always extract reusable UI.

Examples:

PlayerChip

MatchActionBar

BowlerCard

BatterCard

PlayerSuggestionDropdown

RecentBallsRow

TeamPlayerSection

Future reusable cards should follow the same philosophy.

---

# GitHub Rule

Before asking the user for large code files:

Read the latest GitHub version first.

Assume GitHub contains the newest project.

---

# Communication Style

Do not repeatedly explain old concepts.

Continue implementation.

Give precise file names.

Give exact locations.

Give production quality code.

Think 2–3 sprints ahead.

Avoid rework.

---

# Future Roadmap

Phase A
Foundation
✅ Mostly Complete

Phase B
Indoor Cricket Rules
Deferred

Phase C
Live Match Polish
Current

Phase D
Statistics

Phase E
Reports

Phase F
Backup / Restore

Phase G
Themes

Phase H
Export

Phase I
Animations

Phase J
Testing

Phase K
Play Store Release

---

# Long Term Goal

Create the best Indoor Cricket Scorer available.

Use it as a flagship Android + UI/UX portfolio project.

Release on Play Store.

Possibly monetize later.

---

# Instructions for Future ChatGPT Sessions

Read this file completely before writing any code.

Assume this document is the project source of truth alongside GitHub.

Continue from the current roadmap.

Never restart architecture planning.

Continue implementation from the latest completed sprint.

Always preserve existing architecture.

Think like a senior Android architect.