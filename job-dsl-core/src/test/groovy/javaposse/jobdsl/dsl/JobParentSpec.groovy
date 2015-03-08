package javaposse.jobdsl.dsl

import javaposse.jobdsl.dsl.jobs.BuildFlowJob
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
import javaposse.jobdsl.dsl.jobs.MatrixJob
import javaposse.jobdsl.dsl.jobs.MavenJob
import javaposse.jobdsl.dsl.jobs.MultiJob
import javaposse.jobdsl.dsl.jobs.WorkflowJob
import javaposse.jobdsl.dsl.views.BuildMonitorView
import javaposse.jobdsl.dsl.views.BuildPipelineView
import javaposse.jobdsl.dsl.views.DeliveryPipelineView
import javaposse.jobdsl.dsl.views.ListView
import javaposse.jobdsl.dsl.views.NestedView
import javaposse.jobdsl.dsl.views.SectionedView
import spock.lang.Specification

class JobParentSpec extends Specification {
    JobParent parent = Spy(JobParent)
    JobManagement jobManagement = Mock(JobManagement)

    def setup() {
        parent.jm = jobManagement
    }

    def 'default view type deprecated variant'() {
        when:
        View view = parent.view {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof ListView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'list view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.ListView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof ListView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'list view'() {
        when:
        View view = parent.listView('test') {
        }

        then:
        view.name == 'test'
        view instanceof ListView
        parent.referencedViews.contains(view)
    }

    def 'build pipeline view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.BuildPipelineView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof BuildPipelineView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'build pipeline view'() {
        when:
        View view = parent.buildPipelineView('test') {
        }

        then:
        view.name == 'test'
        view instanceof BuildPipelineView
        parent.referencedViews.contains(view)
    }

    def 'build monitor view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.BuildMonitorView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof BuildMonitorView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'build monitor view'() {
        when:
        View view = parent.buildMonitorView('test') {
        }

        then:
        view.name == 'test'
        view instanceof BuildMonitorView
        parent.referencedViews.contains(view)
    }

    def 'sectioned view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.SectionedView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof SectionedView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'sectioned view'() {
        when:
        View view = parent.sectionedView('test') {
        }

        then:
        view.name == 'test'
        view instanceof SectionedView
        parent.referencedViews.contains(view)
    }

    def 'nested view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.NestedView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof NestedView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'nested view'() {
        when:
        View view = parent.nestedView('test') {
        }

        then:
        view.name == 'test'
        view instanceof NestedView
        parent.referencedViews.contains(view)
    }

    def 'delivery pipeline view deprecated variant'() {
        when:
        View view = parent.view(type: ViewType.DeliveryPipelineView) {
            name 'test'
        }

        then:
        view.name == 'test'
        view instanceof DeliveryPipelineView
        parent.referencedViews.contains(view)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'delivery pipeline view'() {
        when:
        View view = parent.deliveryPipelineView('test') {
        }

        then:
        view.name == 'test'
        view instanceof DeliveryPipelineView
        parent.referencedViews.contains(view)
    }

    def 'folder deprecated variant'() {
        when:
        Folder folder = parent.folder {
            name 'test'
        }

        then:
        folder.name == 'test'
        parent.referencedJobs.contains(folder)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'folder'() {
        when:
        Folder folder = parent.folder('test') {
        }

        then:
        folder.name == 'test'
        parent.referencedJobs.contains(folder)
    }

    def 'default config file deprecated variant'() {
        when:
        ConfigFile configFile = parent.configFile {
            name 'test'
        }

        then:
        configFile.name == 'test'
        configFile.type == ConfigFileType.Custom
        parent.referencedConfigFiles.contains(configFile)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'custom config file deprecated variant'() {
        when:
        ConfigFile configFile = parent.configFile(type: ConfigFileType.Custom) {
            name 'test'
        }

        then:
        configFile.name == 'test'
        configFile.type == ConfigFileType.Custom
        parent.referencedConfigFiles.contains(configFile)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'custom config file'() {
        when:
        ConfigFile configFile = parent.customConfigFile('test') {
        }

        then:
        configFile.name == 'test'
        configFile.type == ConfigFileType.Custom
        parent.referencedConfigFiles.contains(configFile)
    }

    def 'Maven settings config file deprecated variant'() {
        when:
        ConfigFile configFile = parent.configFile(type: ConfigFileType.MavenSettings) {
            name 'test'
        }

        then:
        configFile.name == 'test'
        configFile.type == ConfigFileType.MavenSettings
        parent.referencedConfigFiles.contains(configFile)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'Maven settings config file'() {
        when:
        ConfigFile configFile = parent.mavenSettingsConfigFile('test') {
        }

        then:
        configFile.name == 'test'
        configFile.type == ConfigFileType.MavenSettings
        parent.referencedConfigFiles.contains(configFile)
    }

    def 'readFileInWorkspace from seed job'() {
        jobManagement.readFileInWorkspace('foo.txt') >> 'hello'

        when:
        String result = parent.readFileFromWorkspace('foo.txt')

        then:
        result == 'hello'

        when:
        parent.readFileFromWorkspace(null)

        then:
        thrown(IllegalArgumentException)

        when:
        parent.readFileFromWorkspace('')

        then:
        thrown(IllegalArgumentException)
    }

    def 'streamFileFromWorkspace'() {
        InputStream inputStream = Mock(InputStream)
        jobManagement.streamFileInWorkspace('foo.txt') >> inputStream

        when:
        InputStream result = parent.streamFileFromWorkspace('foo.txt')

        then:
        result == inputStream

        when:
        parent.readFileFromWorkspace(null)

        then:
        thrown(IllegalArgumentException)

        when:
        parent.readFileFromWorkspace('')

        then:
        thrown(IllegalArgumentException)
    }

    def 'readFileInWorkspace from other job'() {
        jobManagement.readFileInWorkspace('my-job', 'foo.txt') >> 'hello'

        when:
        String result = parent.readFileFromWorkspace('my-job', 'foo.txt')

        then:
        result == 'hello'

        when:
        parent.readFileFromWorkspace('my-job', null)

        then:
        thrown(IllegalArgumentException)

        when:
        parent.readFileFromWorkspace('my-job', '')

        then:
        thrown(IllegalArgumentException)

        when:
        parent.readFileFromWorkspace(null, 'foo.txt')

        then:
        thrown(IllegalArgumentException)

        when:
        parent.readFileFromWorkspace('', 'foo.txt')

        then:
        thrown(IllegalArgumentException)
    }

    def 'freeStyleJob deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.Freeform) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'job is an alias for freeStyleJob'() {
        when:
        FreeStyleJob job = parent.job('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'freeStyleJob'() {
        when:
        FreeStyleJob job = parent.freeStyleJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'buildFlowJob deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.BuildFlow) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'buildFlowJob'() {
        when:
        BuildFlowJob job = parent.buildFlowJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'matrixJob deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.Matrix) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'matrixJob'() {
        when:
        MatrixJob job = parent.matrixJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'mavenJob deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.Maven) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'mavenJob'() {
        when:
        MavenJob job = parent.mavenJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'multiJob deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.Multijob) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'multiJob'() {
        when:
        MultiJob job = parent.multiJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }

    def 'workflow deprecated variant'() {
        when:
        Job job = parent.job(type: JobType.Workflow) {
            name 'test'
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
        2 * jobManagement.logDeprecationWarning()
    }

    def 'workflow'() {
        when:
        WorkflowJob job = parent.workflowJob('test') {
        }

        then:
        job.name == 'test'
        parent.referencedJobs.contains(job)
    }
}
