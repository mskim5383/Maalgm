"""
simple view logic.
"""


from django.http import HttpResponse
import json


def grpc_request(request):
    post_data = json.dumps({'number': 5})
    return HttpResponse(post_data,
                        content_type='application/json')
