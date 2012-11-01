package com.netflexitysolutions.software.bam.ui



import org.junit.*
import grails.test.mixin.*

@TestFor(FlowController)
@Mock(Flow)
class FlowControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/flow/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.flowInstanceList.size() == 0
        assert model.flowInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.flowInstance != null
    }

    void testSave() {
        controller.save()

        assert model.flowInstance != null
        assert view == '/flow/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/flow/show/1'
        assert controller.flash.message != null
        assert Flow.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/flow/list'

        populateValidParams(params)
        def flow = new Flow(params)

        assert flow.save() != null

        params.id = flow.id

        def model = controller.show()

        assert model.flowInstance == flow
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/flow/list'

        populateValidParams(params)
        def flow = new Flow(params)

        assert flow.save() != null

        params.id = flow.id

        def model = controller.edit()

        assert model.flowInstance == flow
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/flow/list'

        response.reset()

        populateValidParams(params)
        def flow = new Flow(params)

        assert flow.save() != null

        // test invalid parameters in update
        params.id = flow.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/flow/edit"
        assert model.flowInstance != null

        flow.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/flow/show/$flow.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        flow.clearErrors()

        populateValidParams(params)
        params.id = flow.id
        params.version = -1
        controller.update()

        assert view == "/flow/edit"
        assert model.flowInstance != null
        assert model.flowInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/flow/list'

        response.reset()

        populateValidParams(params)
        def flow = new Flow(params)

        assert flow.save() != null
        assert Flow.count() == 1

        params.id = flow.id

        controller.delete()

        assert Flow.count() == 0
        assert Flow.get(flow.id) == null
        assert response.redirectedUrl == '/flow/list'
    }
}
