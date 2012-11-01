package com.netflexitysolutions.software.bam.ui



import org.junit.*
import grails.test.mixin.*

@TestFor(StageController)
@Mock(Stage)
class StageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stageInstanceList.size() == 0
        assert model.stageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stageInstance != null
        assert view == '/stage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stage/show/1'
        assert controller.flash.message != null
        assert Stage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stage/list'

        populateValidParams(params)
        def stage = new Stage(params)

        assert stage.save() != null

        params.id = stage.id

        def model = controller.show()

        assert model.stageInstance == stage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stage/list'

        populateValidParams(params)
        def stage = new Stage(params)

        assert stage.save() != null

        params.id = stage.id

        def model = controller.edit()

        assert model.stageInstance == stage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stage/list'

        response.reset()

        populateValidParams(params)
        def stage = new Stage(params)

        assert stage.save() != null

        // test invalid parameters in update
        params.id = stage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stage/edit"
        assert model.stageInstance != null

        stage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stage/show/$stage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stage.clearErrors()

        populateValidParams(params)
        params.id = stage.id
        params.version = -1
        controller.update()

        assert view == "/stage/edit"
        assert model.stageInstance != null
        assert model.stageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stage/list'

        response.reset()

        populateValidParams(params)
        def stage = new Stage(params)

        assert stage.save() != null
        assert Stage.count() == 1

        params.id = stage.id

        controller.delete()

        assert Stage.count() == 0
        assert Stage.get(stage.id) == null
        assert response.redirectedUrl == '/stage/list'
    }
}
