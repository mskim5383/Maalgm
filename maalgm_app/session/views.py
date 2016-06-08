from django.shortcuts import render
from maalgm_app.settings import GRPC_IP, GRPC_PORT
import session_client

# Create your views here.
def user_login(request):
    if _is_logged_in(request):
        return redirect('/')
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        sessionId = session_client.login(username, password, GRPC_IP, GRPC_PORT)

        if sessionId:
            request.session['sessionId'] = sessionId
            return redirect(request.POST.get('next', '/'))
        else:
            return render(request, 'session/login.html',
                          {'error': 'Invalid login',
                           'next': request.POST.get('next', '/')})
    return render(request, 'session/login.html',
                    {'next': request.GET.get('next', '/')})


def user_logout(request):
    if _is_logged_in(request):
        session_client.logout(request.session.get('sessionId', ''), GRPC_IP, GRPC_PORT)
    return redirect('/session/login')


def register(request):
    if _is_logged_in(request):
        return redirect('/')
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        status = session_client(username, password, GRPC_IP, GRPC_PORT)
        if status == 200:
            login(username, password, GRPC_IP, GRPC_PORT)
            return redirect('/')
    else:
        username = ''
    return render(request, 'session/register.html',
                    {'username': username})

def _is_logged_in(request):
    sessionId = request.session.get('sessionId', '')
    if sessionId:
        username = session_client.get_session_data(sessionId, GRPC_IP, GRPC_PORT)
        return username
    return ''
